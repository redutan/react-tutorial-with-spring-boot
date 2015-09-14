// tutorial1-raw.js
var CommentBox = React.createClass({
  render: function() {
    return (
      React.createElement("div", {className:"commentBox"},
        "안녕!, 난 댓글 박스야"
      )
    );
  }
});

React.render(
  React.createElement(CommentBox, null),
  document.getElementById("content")
)
