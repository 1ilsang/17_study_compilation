var crypto = require('crypto');

var Schema = { };

Schema.createSchema = function(mongoose){
    //Schema define
    UserSchema = mongoose.Schema({
        email : {type:String, 'default':''}
        ,hashed_password: {type:String, 'default':' '}
        ,salt:{type:String}
        ,name: {type:String, index:'hashed', 'default':' '}
        ,created_at: {type:Date, index:{unique:false}, 'default':Date.now}
        ,update_at: {type:Date, index:{unique:false}, 'default':Date.now}
        ,provider: {type:String, 'default':''}
        ,authToken : {type:String, 'default' : ''}
        ,facebook : { }
    });
    UserSchema.virtual('password').set(function(password){
        this._password = password;
        this.salt = this.makeSalt();
        this.hashed_password = this.encryptPassword(password);
        console.log('virtual pw call : ' + this.hashed_password);
    }).get(function(){return this._password});
    UserSchema.method('encryptPassword', function(plainText, inSalt){
        if(inSalt){
            return crypto.createHmac('sha1',inSalt).update(plainText).digest('hex');
        }else{
            return crypto.createHmac('sha1',this.salt).update(plainText).digest('hex');
        }
    });
    UserSchema.method('makeSalt',function(){
        return Math.round((new Date().valueOf() * Math.random()))+'';
    });
    UserSchema.method('authenticate',function(plainText,inSalt,hashed_password){
        if(inSalt){
            console.log('authenticate(2p) call : '+plainText+' -> '+this.encryptPassword(plainText, inSalt),hashed_password);
            return this.encryptPassword(plainText,inSalt)===hashed_password;
        }else{
            console.log('authenticate call : '+plainText+' -> '+this.encryptPassword(plainText),this.hashed_password);
            return this.encryptPassword(plainText,inSalt) === this.hashed_password;
        }
    });
//    UserSchema.path('email').validate(function(email){
//        return email.length;
//    }, 'email 칼럼의 값이 없습니다.');
//    UserSchema.path('hashed_password').validate(function(hashed_password){
//        return hashed_password.length;
//    }, 'hashed_password 칼럼의 값이 없습니다.');
    UserSchema.static('findByEmail',function(id,callback){
        return this.find({email:email}, callback);
    });
    UserSchema.static('findAll',function(callback){
        return this.find({ }, callback);
    });

    return UserSchema;
};

module.exports = Schema;