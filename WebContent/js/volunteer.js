/** 
 * cellFormatter
 */
cellFormatter["name"] = function ( data, type, full ) {
   return '<img disable style="margin-right:10px" src="'+full.iconpath+'" width="60px" height="50px" onerror="this.src=\'img/volunteer.png\'"/>' + data;
}
cellFormatter["sex"] = function ( data, type, full ) {
    if(data == 1){
        return '男';
    }else if(data == 2){
        return '女';
    }else{
       return '未知';
    } 
}
cellFormatter["status"] = function ( data, type, full ) {
    //0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    if(data == 0){
        return '已注册';
    }else if(data == 1){
        return '已审核';
    }else if(data = 2){
       return '已面试';
    }else if(data == 3){
        return '正在服务期';
    }else if(data == 4){
        return '已注销';
    }else{
       return '未知';
    } 
}
cellFormatter["registerFrom"] = function ( data, type, full ) {
    //1=医院,2=微信
    if(data == 1){
        return '医院';
    }else if(data == 2){
        return '微信';
    }else{
       return '未知';
    } 
}