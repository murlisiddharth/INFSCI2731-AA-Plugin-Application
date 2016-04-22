/* 
 *  @author Siwei Jiao
 */

/**
 * check availability of user entered email 
 * @returns {undefined}
 */
function checkEmail() {
    var email = $.trim($("#inputEmail").val());
    $("#emailMsg").removeClass("errMsg");
    $("#emailMsg").removeClass("avaliable");
    $("#inputEmail").parent().removeClass("has-error");
    if (email.length >= 1) {

        var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!pattern.test(email)) {
            $("#emailMsg").html("Please enter a valid email address.");
            $("#emailMsg").addClass("errMsg");
            $("#inputEmail").parent().addClass("has-error");
        } else {
            $("#inputEmail").parent().removeClass("has-error");
            $("#emailMsg").text("");
            $("#emailMsg").html("<span class=\"checking\">Checking email availability...</span>");
            $.ajax({
                type: "POST",
                url: "CheckEmailAvailability",
                data: "email=" + email,
                success: function (data) {

                    if (data === "notAvailable") {
                        $("#emailMsg").html("Sorry, " + email + " is not available.");
                        $("#emailMsg").addClass("errMsg");
                        $("#inputEmail").parent().addClass("has-error");
                    } else if (data === "available") {
                        $("#emailMsg").html(email + " is available.");
                        $("#emailMsg").addClass("avaliable");
                    }
                }
            });
        }
    } else {
        $("#inputEmail").parent().addClass("has-error");
        $("#emailMsg").addClass("formErrorMsg");
        $("#emailMsg").html("Email address cannot be empty.");
    }
}

/**
 * check if retyped password match the first entry
 * @returns {undefined}
 */
function comfirmRetypePassword() {
    var inputPassword = $("#inputPassword").val();
    var inputRetypePassword = $("#inputRetypePassword").val();
    $("#errRetypePw").parent().removeClass("has-error");
    $("#errRetypePw").text("");

    if ($("#inputPassword").parent().hasClass("has-error") || inputPassword === "") {
        $("#inputRetypePassword").parent().addClass("has-error");
        $("#errRetypePw").html("Please enter a valid password first!");
    } else {
        if(inputRetypePassword.length >=1) {
            if (inputRetypePassword === inputPassword) {
                $("#inputRetypePassword").parent().removeClass("has-error");
                $("#errRetypePw").text("");
            } else {
                $("#inputRetypePassword").parent().addClass("has-error");
                $("#errRetypePw").text("Password doesn't match!");
            }           
        }else {
            $("#inputRetypePassword").parent().addClass("has-error");
            $("#errRetypePw").text("Please type your password again.");
            
        }

    }
}


//////////////////////////////////////////////////////////////////////
// get whether the password is strong enough 
//////////////////////////////////////////////////////////////////////
function checkStrongOfPassword() {
    var inputPassword = $("#inputPassword").val();
    var modes = 0;
    $("#inputPassword").parent().removeClass("has-error");
    $("#errStrongLevel").text("");
    if (inputPassword.length < 8)
    {
        $("#inputPassword").parent().addClass("has-error");
        $("#errStrongLevel").html("Password must have 8 characters or more!");
        return 0;
    }
    if (/\d/.test(inputPassword))
        modes++; //number
    if (/[a-z]/.test(inputPassword))
        modes++; //lowercase
    if (/[A-Z]/.test(inputPassword))
        modes++; //uppercase  
    if (/\W/.test(inputPassword))
        modes++; //special char
    
    

    switch (modes) {
        case 0:
            $("#inputPassword").parent().addClass("has-error");
            $("#errStrongLevel").html("Password must have 8 characters or more!");
        case 1:
        case 2:
            $("#inputPassword").parent().addClass("has-error");
            $("#errStrongLevel").html("Password must have 3 types of Uppercase, Lowercase, number, Special Character");
            //return 1;
            break;
        case 3:
        case 4:
            $("#inputPassword").parent().removeClass("has-error");
            $("#errStrongLevel").text("");
            //return 3;
            break;

    }
}


