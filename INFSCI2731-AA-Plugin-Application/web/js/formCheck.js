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
                    
                    if(data === "notAvailable") {
                        $("#emailMsg").html("Sorry, " + email + " is not available.");
                        $("#emailMsg").addClass("errMsg"); 
                        $("#inputEmail").parent().addClass("has-error");                      
                    }else if(data === "available"){
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
    if ($("#inputPassword").hasClass("has-error") || inputPassword === "") {
        $("#errRetypePw").html("Please enter a valid password above!");        
    }else {
        if(inputRetypePassword === inputPassword) {
            $("#errRetypePw").parent().removeClass("has-error");
            $("#errRetypePw").text("");
        }else {
            $("#errRetypePw").text("Password doesn't match!");
        }
                    
    }          
}