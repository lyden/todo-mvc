app = window.app || {};

var u = app.Util = {};

u.emitter = function(obj) {
	var o = obj || {}, jq = $(o);
	_.each(['triggerHandler','on','off','one'], function(val) {
		var key = val === 'triggerHandler' ? 'trigger' : val;
		o[key] = function() { jq[val].apply(
            jq, arguments); return o; };
	}); return o;
};

u.onEnter = function(el, callback) {
	var ENTER_KEY_CODES = [ 10, 13 ];
	$(el).on('keypress', function(e) {
		if (ENTER_KEY_CODES.indexOf(e.which) >= 0) {
			var args = Array.prototype.slice.call(arguments);
			callback.apply(this, args);
		}
	});
};