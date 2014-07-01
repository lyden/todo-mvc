/**
 * Karma Configuration
 */
module.exports = function(config) {
	  config.set({
	    basePath: '..',
// frameworks: ['jasmine', 'requirejs'],
	    frameworks: ['jasmine'],
	    files: [
	    	'vendor/jquery.js',
	    	'vendor/underscore.js',
	    	'app/*.js',
	    	'test/*Spec*.js',
	    ],

	    exclude: ['Gruntfile.js'],
	    reporters: ['progress', 'coverage'],
	    port: 9876,
	    colors: true,
	    logLevel: config.LOG_INFO,
	    autoWatch: true,
	    browsers: ['PhantomJS', 'Chrome', 'Firefox'],
	    captureTimeout: 60000,
	    singleRun: true,
	    preprocessors: {
			'app/**/*.js': ['coverage'],
			'dist/**/*.js': ['coverage'],
		},
	    coverageReporter : {
	    	type: 'html',
	    	dir: 'test/coverage',
	    },
	});
};
