$('.ui.checkbox')
    .checkbox()
;

$('.ui.accordion')
    .accordion()
;


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

// Ask Question

var converter = new Showdown.converter();

$(function () {

    $('#user-input').keyup(function () {

        var div = $('#wiki-style').html(converter.makeHtml(this.value));

        $('#preview-container, #user-input').css({
            height: div.outerHeight() + 40
        })
    });
});

var foo = function () {

//           var userInput = document.getElementById('user-input');
//
//           console.log(userInput.innerHTML);
//
//           document.getElementById('wiki-style').innerHTML = converter.makeHtml(userInput.innerHTML);

    var bar = $('#question-content');

    $('#question-preview').html(converter.makeHtml(bar.text()));
};


$('.ui.popup').popup();


/* Form validation */
$('.ui.form')
    .form({
        name: {
            identifier  : 'name',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Please enter your user name'
                }
            ]
        },
        email: {
            identifier : 'email',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Please enter a email'
                }
            ]
        },
        password: {
            identifier : 'password',
            rules: [
                {
                    type   : 'empty',
                    prompt : 'Please enter a password'
                },
                {
                    type   : 'length[6]',
                    prompt : 'Your password must be at least 6 characters'
                }
            ]
        }
    })
;