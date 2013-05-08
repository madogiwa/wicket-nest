wicket-nest is a very simple library for Apache Wicket. You can write java code which reflected the structure of the HTML code.

##Examples##

    <!-- HTML Template -->
    <form wicket:id="form" action="...">
      <input wicket:id="email" type="text" />
    </form>

The library has only one class named With.


    public MyPage extend WebPage {
        ...
        Form form = new Form("form");
        add(new With(form) {   // form is added to WebPage
            {
                TextField nameField = new TextField("email");
                add(nameField);    // nameField is added to form
            }
        });
        ...
    }
