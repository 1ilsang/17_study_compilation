// mysql 연동 확인
var mysql = require('mysql');
var dbConfig = ({
    //notOpen
}); // database 연동 정보

var pool = mysql.createPool(dbConfig); // create Pool 연동

function getPool(){
    console.log('db success');
 return pool;
}

exports.getPool = getPool; // getPool() 사용하도록 반환