app = window.app || {};

(function() {
	var m = app.Models = window.app.Models || {};

	m.todos = [];

	m.Todo = function(text, completed) {
		return {
			description: text || 'none',
			completed: completed || false,
		};
	};

})();