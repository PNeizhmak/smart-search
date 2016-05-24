var core = {};
core.getServiceUrl = function (baseUrl) {
    var form = '#smart-search-form';
    var platform = $(form).find('select#Platform').val();
    var apiMethod = $(form).find('select#Search-param').val();
    var value = $(form).find('input#search-value').val();
    return baseUrl + platform + "/" + apiMethod + "/" + value;
};

$(function () {

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