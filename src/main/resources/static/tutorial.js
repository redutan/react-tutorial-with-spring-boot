/* 구조화된 코드 구조
 - CommentBox
 - CommentList
 - Comment
 - CommentForm
 */

var CommentBox = React.createClass({
    loadComponentFromServer: function() {
        $.ajax({
            url: this.props.url
            ,dataType: "json"
            ,cache: false
            ,success: function(data) {
                this.setState({data: data});
            }.bind(this)
            ,error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    }
    ,handleCommentSubmit: function(comment) {
        $.ajax({
            url: this.props.url
            ,contentType: "application/json"
            ,dataType: 'json'
            ,cache: false
            ,type: 'POST'
            ,data: JSON.stringify(comment)
            ,success: function(data) {
                console.log("CommentBox.handleCommentSubmit.success");
                console.log(data);
                this.setState({data: data});
            }.bind(this)
            ,error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    }
    ,getInitialState: function () {
        return {data: []}
    }
    ,componentDidMount: function() {
        console.log("CommentBox.componentDidMount");
        this.loadComponentFromServer();
        // setInterval(this.loadComponentFromServer, this.props.pollInterval)
    }
    ,render: function () {
        console.log("CommentBox.render");
        return (
            <div className="commentBox">
                <h1>Comment</h1>
                <CommentList data={this.state.data}/>
                <CommentForm onCommentSubmit={this.handleCommentSubmit}/>
            </div>
        );
    }
});

var CommentList = React.createClass({
    render: function () {
        console.log("CommentList.render");
        console.log(this.props.data);
        var commentNodes = this.props.data.map(function (comment) {
            return (
                <Comment author={comment.author}>
                    {comment.text}
                </Comment>
            );
        });
        return (
            <div className="commentList">
                {commentNodes}
            </div>
        );
    }
});

var CommentForm = React.createClass({
    handleSubmit: function(e) {
        e.preventDefault();
        var author = React.findDOMNode(this.refs.author).value.trim();
        var text = React.findDOMNode(this.refs.text).value.trim();
        if (!text || !author) {
            return;
        }
        this.props.onCommentSubmit({author: author, text:text});
        React.findDOMNode(this.refs.author).value = "";
        React.findDOMNode(this.refs.text).value = "";
        return;
    }
    ,render: function () {
        return (
            <form className="commentForm" onSubmit={this.handleSubmit}>
                <input type="text" placeholder="이름" ref="author" />
                <input type="text" placeholder="내용을 입력하세요." ref="text" />
                <input type="submit" value="올리기" />
            </form>
        );
    }
});

var Comment = React.createClass({
    render: function () {
        var rawMarkup = marked(this.props.children.toString(), {sanitize: true});
        return (
            <div className="comment">
                <h2 className="commentAuthor">
                    {this.props.author}
                </h2>
                <span dangerouslySetInnerHTML={{__html: rawMarkup}}/>
            </div>
        );
    }
});

React.render(
    <CommentBox url="/comments" pollInterval={5000} />,
    document.getElementById("content")
)