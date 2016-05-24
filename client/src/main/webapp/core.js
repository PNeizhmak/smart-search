var core = {};
core.getServiceUrl = function (baseUrl) {
    var platform = $('#smart-search-form select#Platform').val();
    var apiMethod = $('#smart-search-form select#Search-param').val();
    var value = $('#smart-search-form input#search-value').val();
    return baseUrl + platform + "/" + apiMethod + "/" + value;
};

$(function() {

    var baseUrl = "http://localhost:8081/";

    $('#smart-search-form').submit(function (e) {
        e.preventDefault();
        var textarea = $('#smart-search-form').find('textarea#result');
        $.ajax({
            url: core.getServiceUrl(baseUrl),
            type: 'get',
            success: function (data) {
                textarea.val(data);
                console.log(data);
            },
            error: function (data) {
                textarea.val(data.valueOf().responseText);
                console.log(data.valueOf());
            }
        });
    });
});