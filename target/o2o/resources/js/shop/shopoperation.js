/**
 *
 */
$(function () {
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = "/o2o/shopadmin/registershop";
    getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                //这边的单引号就是你把它包起来，双引号其实就是你该写属性啥的，就咋写
                data.shopCategoryList.map(function (item,index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item,index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
        $("#submit").click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $("#shop-img")[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual = $('#j_captcha').val();
            if(!verifyCodeActual){
                $.toast('请输入验证码');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            $.ajax({
                url : registerShopUrl,
                type:'POST',
                data:formData,
                //因为要传文件又要传文字，所以是false的
                contentType:false,
                processData:false,
                cache:false,
                success:function (data) {
                    if(data.success){
                        $.toast('提交成功!');
                    }else{
                        $.toast('提交失败!' + data.errMsg);
                    }
                    //无论成功还是失败，都要进行点击来更换验证码
                    $('#captcha_img').click();
                }
            });
        })
    }

});