
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
    },
});