(function() {

	function appReady(callback) {
		var id = setInterval(function() {
			if (app.Ready) {
				clearInterval(id);
				callback();
			}
		}, 500);
	}
	
	describe('Views.TodoList: ', function() {
		var todoList;

		it('addTodo() appends an li containing the name of the todo', function() {

			runs(function() {
				appReady(function() {
					todoList = app.Views.TodoList
				});
			});

			waitsFor(function() {
				return todoList;
			});

			runs(function() {
				todoList.addTodo('my todo');
				expect(todoList.$el.children('li:contains("my todo")').
					length).toEqual(1);
			});
		});
	});

})();
