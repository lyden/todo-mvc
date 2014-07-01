jQuery(function($) {

	var bus = app.Bus = app.Util.emitter();

	var todoListView = app.Views.TodoList(bus);
	var todoFormView = app.Views.TodoForm(bus);
	var appView = app.Views.AppView(todoFormView, todoListView);

	app.Controllers.Todo(bus, todoListView);

	$('body').prepend(appView.$el);

	app.Ready = true;
});