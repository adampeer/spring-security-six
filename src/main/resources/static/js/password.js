// Password strength checker function
const checkPasswordStrength = () => {
  let password = $('#password').val();
  let progressBar = $('.progress-bar');
  let strength = 0;

  // Check if password contains at least 8 characters
  if (password.length >= 8) {
    strength += 25;
  }

  // Check if password contains both upper and lower case characters
  if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
    strength += 25;
  }

  // Check if password contains at least one digit
  if (password.match(/([0-9])/)) {
    strength += 25;
  }

  // Check if password contains at least one special character
  if (password.match(/([!, @, #, $, %, ^, &, *])/)) {
    strength += 25;
  }

  // Update progress bar width and color based on password strength
  progressBar.width(strength + '%');

  // Update text based on password strength
  let text = '';
  if (strength < 50) {
    progressBar.removeClass('bg-warning bg-success').addClass('bg-danger');
    text = 'Weak Password';
  } else if (strength < 75) {
    progressBar.removeClass('bg-danger bg-success').addClass('bg-warning');
    text = 'Medium Password';
  } else {
    progressBar.removeClass('bg-danger bg-warning').addClass('bg-success');
    text = 'Strong Password';
  }

  // Display text
  $('#password-strength-text').text(text);

}

// Attach the password strength checker to the input field's keyup event
$('#password').keyup(function () {
  checkPasswordStrength();
});