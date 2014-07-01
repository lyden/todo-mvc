app = window.app || {};

(function($) {
	var Views = app.Views = window.app.Views || {};

	Views.AppView = function(todoFormView, todoListView) {
		var view = {};

		var $el = view.$el = $('<main>');

		var title = $('<h1>').text('todos');

		$el.append(title, todoFormView.$el, todoListView.$el);

		return app.Views.AppView = view;
	};

})(jQuery);