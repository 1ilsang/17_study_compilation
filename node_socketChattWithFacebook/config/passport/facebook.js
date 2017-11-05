var FacebookStrategy = require('passport-facebook').Strategy;
var config = require('../../config');

module.exports = function(app, passport){
    return new FacebookStrategy({
        clientID : config.facebook.clientID
        ,clientSecret : config.facebook.clientSecret
        ,callbackURL : config.facebook.callbackURL
        ,profileFields : ['id','email','name']
    }, function(accessToken, refreshToken, profile, done){
        console.log('passport의 facebook func call');
        
        var options = {
//            criteria : {'facebook.id' : profile.id}
            'facebook.id' : {$exists:true}
            ,'facebook.id' : profile.id
        };
        
        var database = app.get('database');
//        console.log(database);
        database.UserModel.findOne(options, function(err, user){
            if(err) return done(err);
            
            if(!user){
                console.log('if!user까지 들어왔다');
                var user = new database.UserModel({
                    name : profile.displayName
                    ,email : profile.emails[0].value
                    ,provider : 'facebook'
                    ,facebook : profile._json
                });
            
                user.save(function(err){
                    if(err) console.log('err');
                    return done(err,user);
                });
                console.log('user.save 했다.');
            }else{
                console.log('user가 있으므로 return done');
                return done(err, user);
            }
        });
    });
};