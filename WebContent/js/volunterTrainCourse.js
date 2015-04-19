//clean all options
cellFormatter["status"] = function ( data, type, full ) {
    //0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    if(data == 0){
        return '未通过';
    }else if(data == 1){
        return '通过';
    }else{
       return '未知';
    }
}

$(".form-horizontal.tasi-form [name='createTime_gteq']").attr("data-date-format","yyyy-mm-dd");
$(".form-horizontal.tasi-form [name='createTime_gteq']").datepicker();
$(".form-horizontal.tasi-form [name='createTime_lteq']").attr("data-date-format","yyyy-mm-dd");
$(".form-horizontal.tasi-form [name='createTime_lteq']").datepicker();