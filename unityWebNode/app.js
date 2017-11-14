var express = require('express'),
    http = require('http'),
    path = require('path'),
    bodyParser = require('body-parser');
var app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static('public')); // 정적 파일 제공 

app.set('port', process.env.PORT || 3000);

var server = http.createServer(app).listen(app.get('port'), function() {
    console.log('서버가 시작되었습니다. 포트 : ' + app.get('port'));
});
//php socket open
var io = require('socket.io').listen(999);
console.log('socket server run!!');

io.sockets.on('connection', function(socket) {
    console.log("A user connected !");
});

app.post('/go', function(req, res) {
    var seq = req.body.seq;

    console.log("go post " + req.body.seq);
    io.sockets.emit('go', { seq: seq });
    res.redirect("/");
});

app.post('/res', function(req, res) {
    var seq = req.body.seq;
    var tf = req.body.tf;
    console.log("res " + tf);
    io.sockets.emit('serverResponse', { seq: seq, tf: tf });
    res.redirect("/");
});

app.post('/updateScore', function(req, res) {
    console.log("updateScore post");
    //not Open DB
    io.sockets.emit('updateScore', {
        aScore: "100",
        bScore: "200",
        cScore: "300",
        dScore: "400",
        eScore: "500",
        fScore: "600",
        gScore: "700",
        hScore: "800",
        iScore: "900",
        jScore: "1000",
        kScore: "1100"
    });
    res.redirect("/");
});