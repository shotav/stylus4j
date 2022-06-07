load("stylus.min.js");

stylus.render(styl, {}, (err, css) => {
    send(css);
});