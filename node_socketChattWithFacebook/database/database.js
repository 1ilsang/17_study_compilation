var mongodb = require('mongodb')
    ,mongoose = require('mongoose');

var database = {}

database.init = function(app, config){
    console.log('database.init() func call');
    connect(app, config);
}

function connect(app, config){    
    mongoose.connect(config.db_url);
    database = mongoose.connection;
    
    database.on('error',console.error.bind(console, 'mongoose connection err'));
    database.on('open',function(){
        console.log('DB Connect : '+ config.db_url);
        createUserSchema(app, config);
    });
    database.on('disconnected',connect);
}

function createUserSchema(app, config){
    var schemaLen = config.db_schemas.length;
	console.log('설정에 정의된 스키마의 수 : %d', schemaLen);
	
	for (var i = 0; i < schemaLen; i++) {
		var curItem = config.db_schemas[i];
		// 모듈 파일에서 모듈 불러온 후 createSchema() 함수 호출하기
		var curSchema = require(curItem.file).createSchema(mongoose);
		console.log('%s 모듈을 불러들인 후 스키마 정의함.', curItem.file);
		// User 모델 정의
		var curModel = mongoose.model(curItem.collection, curSchema);
		console.log('%s 컬렉션을 위해 모델 정의함.', curItem.collection);
		// database 객체에 속성으로 추가
		database[curItem.schemaName] = curSchema;
		database[curItem.modelName] = curModel;
		console.log('스키마 이름 [%s], 모델 이름 [%s] 이 database 객체의 속성으로 추가됨.', curItem.schemaName, curItem.modelName);
	}
	app.set('database', database);
	console.log('database 객체가 app 객체의 속성으로 추가됨.');
}
module.exports = database;