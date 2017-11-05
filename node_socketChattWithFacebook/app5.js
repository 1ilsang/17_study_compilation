var express = require('express')
  , http = require('http')
  , path = require('path');
var bodyParser = require('body-parser')
  , cookieParser = require('cookie-parser')
  , static = require('serve-static')
    ,socketio = require('socket.io')
    ,cors = require('cors');
var expressErrorHandler = require('express-error-handler');
var expressSession = require('express-session');
//===== Passport 사용 =====//
var passport = require('passport');
var flash = require('connect-flash');

var config = require('./config');
var database = require('./database/database');
var route_loader = require('./routes/route_loader')
    ,configPassport = require('./config/passport')
    ,userPassport = require('./routes/user_passport');

var app = express()
    ,login_ids = {};

//===== 뷰 엔진 설정 =====//
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
console.log('뷰 엔진이 ejs로 설정되었습니다.');

console.log('config.server_port : %d', config.server_port);
app.set('port', process.env.PORT || config.server_port);
 
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use('/public', static(path.join(__dirname, 'public')));
app.use(cookieParser());
app.use(expressSession({
	secret:'my key',
	resave:true,
	saveUninitialized:true
}));
//===== Passport 사용 설정 =====//
// Passport의 세션을 사용할 때는 그 전에 Express의 세션을 사용하는 코드가 있어야 함
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());
app.use(cors());
//라우팅 정보를 읽어 들여 라우팅 설정
var router = express.Router();
route_loader.init(app, router);

configPassport(app, passport);
userPassport(router, passport);

var errorHandler = expressErrorHandler({
 static: {
   '404': './public/404.html'
 }
});

function sendResponse(socket, command, code, message){
    var statusObj = {command : command, code : code, message : message};
    socket.emit('response', statusObj);
}

function getRoomList(){
    var roomList = [];
    Object.keys(io.sockets.adapter.rooms).forEach(function(roomId){
        console.log('getRLst ::: currnet room id : ' + roomId);
        var outRoom = io.sockets.adapter.rooms[roomId];
        var foundDefault = false;
        var index = 0;
        Object.keys(outRoom).forEach(function(key){
            console.log('#' + index + ' : ' + key + ", " + outRoom[key]);
            
            if(roomId === key){
                foundDefault = true;
                console.log('this is default room.');
            }
            index++;
        });
        
        if(!foundDefault){
            roomList.push(outRoom);
        }
    });
    return roomList;
}

app.use( expressErrorHandler.httpError(404) );
app.use( errorHandler );

var server = http.createServer(app).listen(app.get('port'), function(){
	console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));
	database.init(app, config);
});


var io = socketio.listen(server);
console.log('socket start');
// 클라이언트가 연결했을 때의 이벤트 처리
io.sockets.on('connection', function(socket) {
	console.log('connection info :', socket.request.connection._peername);
    socket.remoteAddress = socket.request.connection._peername.address;
    socket.remotePort = socket.request.connection._peername.port;

    socket.on('login', function(login) {
    	console.log('login 이벤트를 받았습니다.');
        console.log('접속한 소켓의 ID : ' + socket.id);
        login_ids[login.id] = socket.id;
        socket.login_id = login.id;
        console.log('접속한 클라이언트 ID 갯수 : %d', Object.keys(login_ids).length);
        sendResponse(socket, 'login', '200', '로그인되었습니다.');
    });

    socket.on('message', function(message) {
    	console.log('message 이벤트를 받았습니다.');
    	console.dir(message);
        if (message.recepient =='ALL') {
        	console.dir('나를 포함한 모든 클라이언트에게 message 이벤트를 전송합니다.')
            io.sockets.emit('message', message);
        } else {
        	if (message.command == 'chat') {
	        	if (login_ids[message.recepient]) {                       io.sockets.connected[login_ids[message.recepient]].emit('message', message);
	                sendResponse(socket, 'message', '200', '메시지를 전송했습니다.');
	        	} else {
	                sendResponse(socket, 'login', '404', '상대방의 로그인 ID를 찾을 수 없습니다.');
	        	}
        	} else if (message.command == 'groupchat') {
	        	io.sockets.in(message.recepient).emit('message', message);
	            sendResponse(socket, 'message', '200', '방 [' + message.recepient + ']의 모든 사용자들에게 메시지를 전송했습니다.');
        	}
        }
    });
    socket.on('room', function(room) {
    	console.log('room 이벤트를 받았습니다.');
    	console.dir(room);
        if (room.command === 'create') {
        	if (io.sockets.adapter.rooms[room.roomId]) { 
        		console.log('방이 이미 만들어져 있습니다.');
        	} else {
        		console.log('방을 새로 만듭니다.');
        		socket.join(room.roomId);
	            var curRoom = io.sockets.adapter.rooms[room.roomId];
	            curRoom.id = room.roomId;
	            curRoom.name = room.roomName;
	            curRoom.owner = room.roomOwner;
        	}
        } else if (room.command === 'update') {
            var curRoom = io.sockets.adapter.rooms[room.roomId];
            curRoom.id = room.roomId;
            curRoom.name = room.roomName;
            curRoom.owner = room.roomOwner;
        } else if (room.command === 'delete') {
            socket.leave(room.roomId);
            if (io.sockets.adapter.rooms[room.roomId]) { 
            	delete io.sockets.adapter.rooms[room.roomId];
            } else {  // 방이  만들어져 있지 않은 경우
            	console.log('방이 만들어져 있지 않습니다.');
            }
        } else if (room.command === 'join') {  // 방에 입장하기 요청
            socket.join(room.roomId);
            sendResponse(socket, 'room', '200', '방에 입장했습니다.');
        } else if (room.command === 'leave') {  // 방 나가기 요청
            socket.leave(room.roomId);
            sendResponse(socket, 'room', '200', '방에서 나갔습니다.');
        }
        var roomList = getRoomList();
        var output = {command:'list', rooms:roomList};
        console.log('클라이언트로 보낼 데이터 : ' + JSON.stringify(output));
        io.sockets.emit('room', output);
    });
});