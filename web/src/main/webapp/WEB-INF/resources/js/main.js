
var getTagNames = function () {
    var tagsView = '<div class="ui teal labels">',
        tagsLine = '';

    $('input:checkbox[name=tag]:checked').each(function () {
        tagsView += '<a class="ui label">' + this.value + '</a>';
        tagsLine += this.value + ' ';
    });

    $('#tag-view').html(tagsView + '</div>');

    $('input[name=tags]').val(tagsLine.substring(0, tagsLine.length - 1));
};


$('.ui.checkbox').checkbox();


/* error message on login */
$('.message .close').on('click', function() {
    $(this).closest('.message').fadeOut();
});


/* tabs */
$(document).ready(function () {
    $('.demo.menu .item').tab(
        {
            history: false
        }
    );
});

/* launch modal for add tags */

$('.small.modal').modal('attach events', '#add-tag', 'show');


/* help icons */
$('.icon.link').popup({
    on: 'hover'
});

/* Pop-up info about tag */
$('.label').popup({
    on: 'hover'
});


$('.ui.popup').popup();

$('.ui.accordion').accordion();

$('.ui.form').form({
    login: {
        identifier: 'j_username',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your email'
            }
        ]
    },
    j_password: {
        identifier: 'j_password',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    },
    name: {
        identifier: 'name',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    },
    email: {
        identifier: 'email',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    },
    password: {
        identifier: 'password',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    },
    questionTitle: {
        identifier: 'title',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    },
    content: {
        identifier: 'content',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    }
});