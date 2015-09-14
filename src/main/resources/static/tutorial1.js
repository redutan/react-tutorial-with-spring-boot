// tutorial1.js
var CommentBox = React.createClass({
  render: function() {
    return (
      <div className="commentBox">
        안녕. 난 댓글박스야
      </div>
    );
  }
});

React.render(
  <CommentBox/>,
  document.getElementById("content")
)
