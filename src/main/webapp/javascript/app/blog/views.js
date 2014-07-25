App.Views.Blog = Backbone.View.extend({
  render: function(param) {
    return $('#main').html(this.$el.html(_.template($('#main_blog').html(), param)));
  }
});

App.Views.Blogs = Backbone.View.extend({
  events: {
    "blur    .updateBlog": "updateBlog",
    "click   .deleteBlog": "deleteBlog"
  },
  updateBlog: function(event) {
    var model;
    event.preventDefault();
    model = new Blog({
      title: $('#blog_newtitle').val(),
      body: $('#blog_newbody').val()
    });
    if (model.isValid()) {
      return model.save();
    } else {
      return window.alert(model.validationError);
    }
  },
  deleteBlog: function(event) {
    var id;
    if (window.confirm("Are you sure?")) {
      event.preventDefault();
      id = $(event.currentTarget).data('id');
      return blogs.get(id).destroy();
    }
  },
  render: function(param) {
    return this.$el.html(_.template($('#main_blogs').html(), param));
  },
  renew: function() {
    return $.when(blogs.fetch()).done(function() {
      console.log(blogs);
      return $('#main').html(new App.Views.Blogs().render({
        blogs: blogs
      }));
    });
  }
});
