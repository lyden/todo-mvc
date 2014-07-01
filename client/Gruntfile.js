
module.exports = function(grunt) {
	var appJS = 'app/*.js';

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		karma: {
			options: {
				configFile: 'config/karma.conf.js',
			},
			dev: {
				reporters: 'dots',
				browsers: ['PhantomJS'],
			},
			ci: {
				reporters: ['progress', 'coverage'],
				singleRun: true,
			}
		},

		jshint: {
			options: {
				force: true,
				jshintrc: 'config/jshintrc.json',
			},
			all: [appJS],
		},

		jscs: {
			options: {
				config: 'config/jscsrc.json',
				force: true,
			},
			src: appJS,
		},

		uglify: {
			dist: {
				files: {
					'dist/app.min.js': [
						'vendor/jquery.js', 
						'vendor/underscore.js', 
						'app/*.js'
					],
				},
			},
			options: {
				mangle: true,
				compress: true,
				report: 'gzip',
				sourceMap: true,
			},
		},

		watch: {
			files: ['./*', appJS],
			tasks: ['jshint', 'jscs', 'uglify', 'karma:dev'],

			options: {
				livereload: true,
			}
		},
	});

	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-karma');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-jscs-checker');
	grunt.loadNpmTasks('grunt-contrib-uglify');

	grunt.registerTask('default', ['jshint, jscs']);
	grunt.registerTask('verify', ['jshint', 'karma:ci']);
};