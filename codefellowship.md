#Code\Fellowship
##Work done
* Created the following pages
    * Index- Home page that has the option to login or signup. If you are already logged in it says Hello (firstName) and gives the option to logout,
    * Login - a form that takes in a username and password 
    * Signup - a form that takes in username password bio first&last name bday
    * userdetails - welcoms(firstname) shows all posts made bu user and gives the option to add a new post.
* Created fragments
    * head - holds data for bootstrap or any other attributes that would apply to all pages
    * header - holds nav info for all pages
* Added a web security page to 
    * control routes that a user is able to see if loged in or not
* Controllers
    * ApplicationUserController utilizes the following routes
        * @PostMapping("/signup") which routes back to /userdetails upon successful signup.
        * @GetMapping("/userdetails") which routes back to /userdetails shows user data
        * @PostMapping("/userdetails") used to save a new post and reroute to /userdetails.
        * @GetMapping("/signup") takes you to signup page
        * @GetMapping("/login") takes you to login page