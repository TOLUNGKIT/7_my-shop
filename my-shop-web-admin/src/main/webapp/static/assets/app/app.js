var App = function () {

    var _masterCheckbox;
    var _checkbox;

    /**
     * 私有方法，初始化 ICheck
    */
    var handlerInitCheckbox = function () {
        //iCheck for checkbox and radio inputs 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制端 Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        //获取全部 Checkbox 集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            console.log(e.target.checked);
            //返回true 表示未选中
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }

            //选中状态
            else{
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 公共
     */
    return{
        init: function(){
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        }
    }
}();

$(document).ready(function () {
    App.init();
});