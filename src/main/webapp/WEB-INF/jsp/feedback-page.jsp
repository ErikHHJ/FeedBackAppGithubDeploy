<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow p-4">
        <h2 class="mb-3">Feedback for: ${lecture.id}</h2>

        <p class="text-success">${message}</p>

        <form action="/feedback/${lecture.token}" method="post">

            <!-- Text -->
            <div class="mb-3">
                <label class="form-label">Your feedback</label>
                <textarea class="form-control" name="text" rows="4">${feedBack.text}</textarea>
            </div>

            <!-- Rating -->
            <div class="mb-3">
                <label class="form-label">Rating</label>
                <select class="form-select" name="rating">
                    <option value="">Choose...</option>
                    <option value="1">1 - Bad</option>
                    <option value="2">2</option>
                    <option value="3">3 - OK</option>
                    <option value="4">4</option>
                    <option value="5">5 - Excellent</option>
                </select>
            </div>

            <button class="btn btn-primary">Submit</button>

        </form>
    </div>

</div>

</body>
</html>