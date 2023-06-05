package BackEnd.SoloProject_ToDo.todo.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_NOT_FOUND(404, "TODO not found"),
    TODO_EXISTS(409, "TODO exists"),
    CANNOT_CHANGE_TODO(403, "TODO can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL server Error"),
    INVALID_TODO_STATUS(400, "Invalid TODO status");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
