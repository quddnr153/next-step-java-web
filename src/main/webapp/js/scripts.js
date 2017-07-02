$(document).ready(function () {/* jQuery toggle layout */
	$('#btnToggle').click(function () {
		if ($(this).hasClass('on')) {
			$('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
			$(this).removeClass('on');
		}
		else {
			$('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
			$(this).addClass('on');
		}
	});
});

$('#answerSubmit').on('click', addAnswer);

function addAnswer(e) {
	e.preventDefault();

	var queryString = $('#answerForm').serialize();

	console.log(queryString);

	$.ajax({
		type: 'post',
		url: '/api/qna/addAnswer',
		data: queryString,
		dataType: 'json',
		error: function () {
			alert("error");
		},
		success: function (json, status) {
			var answerTemplate = $("#answerTemplate").html();
			var template = answerTemplate.format(json.writer, json.createdDate, json.contents, json.answerId, json.answerId);
			$(".qna-comment-slipp-articles").prepend(template);
		}
	});
}
