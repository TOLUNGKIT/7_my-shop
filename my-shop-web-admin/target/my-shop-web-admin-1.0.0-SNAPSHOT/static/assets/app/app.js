var App = function () {

    // iCheck
    var _masterCheckbox;
    var _checkbox;

    //用于存放 ID 的数组
    var _idArray;

    // 默认的 Dropzone 参数
    var defaultDropzoneOpts = {
        url: "",
        paramName: "dropzFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

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
            //console.log(e.target.checked);
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
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        // 将选中元素的ID 放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if(_id != null && _id != "undefined" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if(_idArray.length === 0){
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确定删除数据项吗？");
        }

        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModalOk").bind("click", function () {
            del()
        });

        /**
         * 当前私有函数的私有函数，删除数据
         */
        function del() {
            $("#modal-default").modal("hide");

            // 如果没有选择数据项的处理
            if(_idArray.length === 0){
                //...

            }

            // 否则删除操作
            else{
                setTimeout(function () {
                    $.ajax({
                        "url" : url,
                        "type" : "POST",
                        "data" : {"ids" : _idArray.toString()},
                        "dataType" : "JSON",
                        "success" : function (data) {
                            //请求成功后，无论是成功还是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的 click 事件
                            $("#btnModalOk").unbind("click");

                            //请求成功
                            if(data.status === 200){
                                //刷新页面
                                $("#btnModalOk").bind("click", function () {
                                    window.location.reload();
                                });
                            }

                            //请求失败
                            else {
                                //确定按钮的事件改为隐藏模态框
                                $("#btnModalOk").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });
                            }

                            //因为无论如何都需要提示信息，所以这里的模态框是必须要用的
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);
            }
        }
    };

    /**
     * 初始化 DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });

        return _dataTable;
    };

    /**
     * 初始化 zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback){
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };

        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click",function (){
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            // 未选择
            if (nodes.length == 0){
                alert("请选择一个节点");
            }
            // 已选择
            else {
                callback(nodes);
            }
        });
    };

    /**
     * 初始化 Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts){
        // 关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false;
        // JQ的继承
        $.extend(defaultDropzoneOpts, opts);
        // console.log(defaultDropzoneOpts.paramName);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };


    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        //这里是通过 Ajax 请求 html 的方式将 jsp 装载进模态框中
        $.ajax({
            url : url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 通过 id 删除单条用户数据
     * @param url
     */
    var handlerDelete = function (url) {
        $("#modal-message").html("您确定删除数据项吗？");
        $("#modal-default").modal("show");
        //如果用户选择了数据项则调用删除方法
        $("#btnModalOk").bind("click", function () {
            del()
        });

        function del(){
            $("#modal-default").modal("hide");

            $.ajax({
                url : url,
                type : "get",
                dataType: "JSON",
                success: function (data) {
                    //请求成功后，无论是成功还是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的 click 事件
                    $("#btnModalOk").unbind("click");

                    //请求成功
                    if(data.status === 200){
                        //刷新页面
                        $("#btnModalOk").bind("click", function () {
                            window.location.reload();
                        });
                    }

                    //请求失败
                    else {
                        //确定按钮的事件改为隐藏模态框
                        $("#btnModalOk").bind("click", function () {
                            $("#modal-default").modal("hide");
                        });
                    }

                    //因为无论如何都需要提示信息，所以这里的模态框是必须要用的
                    $("#modal-message").html(data.message);
                    $("#modal-default").modal("show");
                }
            });
        }
    };

    /**
     * 公共
     */
    return{
        /**
         * 初始化
         */
        init: function(){

            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 初始化 DataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化 zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback){
            handlerInitZTree(url, autoParam, callback);
        },

        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone: function (opts){
            handlerInitDropzone(opts);
        },

        /**
         * 显示详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url);
        },

        del: function (url) {
            handlerDelete(url);
        }
    }
}();

$(document).ready(function () {
    App.init();
});