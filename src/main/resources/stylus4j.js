load("stylus.js");

stylus.render(styl, {}, (err, css) => {
    send(css);
});