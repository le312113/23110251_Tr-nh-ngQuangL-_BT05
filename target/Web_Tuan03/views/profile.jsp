<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h2>Update Profile</h2>

<form action="profile" method="post" enctype="multipart/form-data" class="row g-3">
    <div class="col-md-6">
        <label class="form-label">Full Name</label>
        <input type="text" name="fullname" class="form-control"/>
    </div>
    <div class="col-md-6">
        <label class="form-label">Phone</label>
        <input type="text" name="phone" class="form-control"/>
    </div>
    <div class="col-md-12">
        <label class="form-label">Profile Image</label>
        <input type="file" name="image" class="form-control"/>
    </div>
    <div class="col-12">
        <button type="submit" class="btn btn-primary">Update</button>
    </div>
</form>
