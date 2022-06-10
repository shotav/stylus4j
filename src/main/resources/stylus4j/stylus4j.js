stylus.render(code, {}, (err, css) => {
    send(css);
});