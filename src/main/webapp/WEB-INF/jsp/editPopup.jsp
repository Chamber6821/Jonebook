<%@ page contentType="text/html;charset=UTF-8" %>
<script src="${pageContext.request.contextPath}/public/editPopup.js"></script>
<div id="edit-modal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 id="edit-header" class="modal-title">
                    How did you get here?
                    <div id="edit-id"></div>
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label for="edit-name">Name</label>
                <input id="edit-name" class="form-control" type="text">

                <label for="edit-email">Email</label>
                <input id="edit-email" class="form-control" type="email">

                <label for="edit-phone">Phone</label>
                <input id="edit-phone" class="form-control" type="text">

                <label for="edit-internalPhone">Internal phone</label>
                <input id="edit-internalPhone" class="form-control" type="text">

                <label for="edit-department">Department</label>
                <select id="edit-department"
                        class="form-control"
                        data-live-search="true">
                </select>

                <label for="edit-posts">Work posts</label>
                <select id="edit-posts"
                        class="form-control"
                        data-live-search="true"
                        multiple data-actions-box="true">
                </select>
            </div>
            <div class="modal-footer">
                <button id="edit-create" type="button" class="btn btn-primary">Create</button>
                <div id="edit-button-group">
                    <button id="edit-save-changes" type="button" class="btn btn-primary">Save changes</button>
                    <button id="edit-delete" type="button" class="btn btn-danger">Delete</button>
                </div>
            </div>
        </div>
    </div>
</div>