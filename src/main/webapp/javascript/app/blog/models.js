App.Models.Blog = Backbone.Model.extend({
  initialize: function() {
    return this.on('sync', function() {
      return new App.Views.Blogs().renew();
    });
  },
  urlRoot: '/blog',
  validate: function(attrs, options) {
    if (!attrs.name) {
      return 'name is required';
    }
  },
  addBlog: function() {
    var route;
    route = '/blog/add';
    return $.ajax({
      url: route.url,
      type: route.method
    }, {
      success: (function(_this) {
        return function(response) {
          return console.log("POST /blog/add success (status: " + response.statusText + ")");
        };
      })(this),
      error: (function(_this) {
        return function(response) {
          return console.log("POST /blog/add failure (status: " + response.statusText + ")");
        };
      })(this)
    });
  }
});

App.Models.Blogs = Backbone.Collection.extend({
  url: '/blog',
  model: App.Models.Blog,
  parse: function(data) {
    console.log(data);
    return data.blogs;
  }
});
