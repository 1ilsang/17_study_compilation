module.exports = {
    server_port : 3000
    ,db_url : 'mongodb://notOpen'
    ,db_schemas: [
        {
            file:'./user_schema'
            ,collection:'users6'
            ,schemaName:'UserSchema'
            ,modelName:'UserModel'
        }
    ]
    ,route_info: [
    ]
    ,facebook:{
        'notOpen'
    }
}
