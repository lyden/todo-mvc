app = window.app || {};
app.Controllers = window.app.Controllers || {};

(function($) {

	app.Controllers.Todo = function(bus, todoListView) {

		bus.on('todo.added', function(e, text) {
			if (text = text.trim(text)) {

				var todoView = todoListView.addTodo(text);
				var todoModel = app.Models.Todo(text);

				todoView._model = todoModel;
				app.Models.todos.push(todoModel);
			}
		});

		bus.on('todo.removed', function(e, view) {
			app.Models.todos = _.without(
				app.Models.todos, view._model);
		});
	};

})(jQuery);