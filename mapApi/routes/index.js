var express = require('express');
var router = express.Router();
var db = require('../db/database.js');

router.get('/', function(req, res, next){
    res.render('index');
});

router.get('/map', function(req,res){
    res.render('map');
});

//router.get('/')

router.post('/map',function(req,res){
    var X = Number(req.body.cenX);
    var Y = Number(req.body.cenY);
    var area = req.body.area;
    var centerX = X - area / 100000;
    var centerY = Y - area / 100000;
    var centerX2 = X + (area / 100000);
    var centerY2 = Y + (area / 100000);
    console.log(X + ' // area : ' + area / 100000);
    console.log(centerX + ' ~ ' + centerX2);
    console.log(centerY + ' ~ ' + centerY2);
//    console.log(X +', ' + Y);
//    res.render('mapRet', {x : X, y: Y});
    db.getPool().getConnection(function (err, connection) {
        connection.query(
            'SELECT * FROM addr WHERE (Y BETWEEN ? AND ?) AND (X BETWEEN ? AND ?)'
            ,[centerY, centerY2, centerX, centerX2]
            , function (err, rows) {
                if (err) console    .error("err : " + err);
                console.log("rows : " + JSON.stringify(rows));
                if(rows[0] == null){
                    res.render('mapNoRet');
                }
                else{
                    res.render('mapRet', {rows: rows});
                }
                connection.release();
        });
    });
});

module.exports = router;