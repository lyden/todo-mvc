app = window.app || {};

(function($) {

	var Views = app.Views = window.app.Views || {},
		Util = app.Util;


	var Todo = Views.Todo = function(bus, text) {
		var view = {};

		var $el = view.$el = $('<li>').addClass('todo');
		var btn = $('<button>').addClass('delete').text('X');
		var label = $('<label>').text(text);

		$el.append(label, btn);

		view.remove = function() {
			$el.remove();
			Todo.instances = _.without(Todo.instances, view);
			bus.trigger('todo.removed', view);
		};

		btn.on('click', view.remove);

		Todo.instances.push(view);
		return view;
	};

	Views.Todo.instances = [];


	Views.TodoList = function(bus) {
		var view = {};

		var $el = view.$el = $('<ul>').addClass('todo-list');

		view.addTodo = function(text) {
			var todo = Views.Todo(bus, text);
			$el.append(todo.$el);
			return todo;
		};

		return Views.TodoList = view;
	};


	Views.TodoForm = function(bus) {
		var view = {};

		var $el = view.$el = $('<input>').attr({
			id: 'new-todo',
			placeholder: 'What needs to be done?',
			autofocus: true,
		}).width('100%');

		app.Util.onEnter($el, function(e) {
			bus.trigger('todo.added', [$el.val()]);
			$el.val('');
		});

		return Views.TodoForm = view;
	};

})(jQuery);