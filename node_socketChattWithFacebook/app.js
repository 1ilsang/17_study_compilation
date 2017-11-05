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

var app = express();

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

app.use( expressErrorHandler.httpError(404) );
app.use( errorHandler );

var server = http.createServer(app).listen(app.get('port'), function(){
	console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));
	database.init(app, config);
});


var io = socketio.listen(server);
console.log('socket start');

io.sockets.on('connection', function(socket) {
	console.log('connection info :', JSON.stringify(socket.request.connection._peername));
//
//	// 소켓 객체에 클라이언트 Host, Port 정보 속성으로 추가
	socket.remoteAddress = socket.request.connection._peername.address;
	socket.remotePort = socket.request.connection._peername.port;
//    
    socket.on('message', function(message){
        console.log('message 이벤트를 받았습니다');
        if(message.recepient === 'ALL'){
            console.dir('나를 포함한 모든 클라에 message 이벤트를 전송');
            io.sockets.emit('message',message);
        }
    });
});