var core = {};
core.getServiceUrl = function (baseUrl) {
    var form = '#smart-search-form';
    var platform = $(form).find('select#Platform').val();
    var apiMethod = $(form).find('select#Search-param').val();
    var value = $(form).find('input#search-value').val();
    var nicknameValue = $(form).find('input#search-value-nickname').val();

    if (nicknameValue != undefined) {

        var twitterExtraParam1 = "'nickname':'" + nicknameValue + "'";
        var twitterExtraParam2 =  "'anotherOneParam':'parse_more_than_one_param'";

        return baseUrl + platform + "/" + apiMethod + "/" + value + ";params=" + twitterExtraParam1 + ";params=" + twitterExtraParam2;
    }

    return baseUrl + platform + "/" + apiMethod + "/" + value;
};

$(function () {

    $('#Platform').on('change', function () {
        $("#search-by-nickname").css('display', (this.value == 'twitter') ? 'block' : 'none');
    });

    var baseUrl = "http://localhost:8081/";

    $('#smart-search-form').submit(function (e) {
        e.preventDefault();
        var textarea = $('#smart-search-form').find('textarea#result');
        $.ajax({
            url: core.getServiceUrl(baseUrl),
            type: 'get',
            success: function (data) {
                textarea.val(JSON.stringify(data));
                console.log(data);
            },
            error: function (data) {
                textarea.val(data.valueOf().responseText);
                console.log(data.valueOf());
            }
        });
    });
});