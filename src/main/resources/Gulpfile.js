var gulp = require('gulp');
var uglify = require('gulp-uglify');
var streamify = require('gulp-streamify');
var rename = require('gulp-rename');
var gulpif = require('gulp-if');
var rev = require('gulp-rev');
var inject = require('gulp-inject');

var browserify = require('browserify');
var babelify = require('babelify');
var del = require('del');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var resolve = require('resolve');
var series = require('stream-series');
var argv = require('yargs').argv;

var production = (process.env.NODE_ENV === 'production' || argv.production);

gulp.task('default', ['clean', 'inject'], function() {});

gulp.task('clean', function(cb) {
  var deleted = del(['./public'], cb);
  return deleted;
});

gulp.task('inject', ['vendor', 'scripts', 'bootstrap'], function() {
  var vendor = gulp.src('./public/scripts/vendor*.js', {read: false});
  var scripts = gulp.src('./public/scripts/scripts*.js', {read: false});
  var bootstrap_min_js = gulp.src("./public/bootstrap/js/bootstrap.min.js", {read: false});
  var bootstrap_min_css = gulp.src("./public/bootstrap/css/bootstrap.min.css", {read: false});
  gulp.src('./app/index.html')
    .pipe(inject(series(vendor, scripts, bootstrap_min_js, bootstrap_min_css), {ignorePath: '/public'}))
    .pipe(gulp.dest('./public'));
});

gulp.task('vendor', ['clean'], function() {
  var b = browserify({debug: !production});

  getNPMPackageIds().forEach(function(id) {
    b.require(resolve.sync(id), {expose: id});
  });

  return bundle('vendor.js', b);
});

gulp.task('scripts', ['clean'], function() {
  var b = browserify({debug: !production})
    .require('./app/scripts/app.js', {entry: true})
    .transform(babelify);

  getNPMPackageIds().forEach(function(id) {
    b.external(id);
  });

  return bundle('scripts.js', b);
});

gulp.task('bootstrap', ['clean'], function () {
    return gulp.src("./node_modules/bootstrap/dist/**/*")
        .pipe(gulp.dest("./public/bootstrap"))
	.on('end', function () {
            del([
                './public/bootstrap/css/*.*',
                '!./public/bootstrap/css/bootstrap.min.css',
                '!./public/bootstrap/css/bootstrap.css.map',
                './public/bootstrap/js/bootstrap.js',
                './public/bootstrap/js/npm.js',
            ]);
        });
});


function bundle(name, b) {
  return b.bundle().pipe(source(name))
    .pipe(gulpif(production, streamify(uglify())))
    .pipe(rename(name.substring(0, name.lastIndexOf('.js')) + '.min.js'))
    .pipe(buffer())
    .pipe(rev())
    .pipe(gulp.dest('./public/scripts'));
}

function getNPMPackageIds() {
  // read package.json and get dependencies' package ids
  var packageManifest = {};
  try {
    packageManifest = require('./package.json');
  } catch (e) {
    // does not have a package.json manifest
  }
  return Object.keys(packageManifest.dependencies) || [];
}
