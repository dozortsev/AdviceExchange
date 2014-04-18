
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
    password: {
        identifier: 'j_password',
        rules: [
            {
                type: 'empty',
                prompt: 'Please enter your password'
            }
        ]
    }
});

console.log('Work');