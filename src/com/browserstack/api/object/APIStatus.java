package com.browserstack.api.object;

public class APIStatus {
    private String used_time;
    private Long total_available_time;
    private Integer running_sessions;
    private Integer sessions_limit;
    private String message;

    public String getUsed_time() {
        return used_time;
    }

    public void setUsed_time(String used_time) {
        this.used_time = used_time;
    }

    public Long getTotal_available_time() {
        return total_available_time;
    }

    public void setTotal_available_time(Long total_available_time) {
        this.total_available_time = total_available_time;
    }

    public Integer getRunning_sessions() {
        return running_sessions;
    }

    public void setRunning_sessions(Integer running_sessions) {
        this.running_sessions = running_sessions;
    }

    public Integer getSessions_limit() {
        return sessions_limit;
    }

    public void setSessions_limit(Integer sessions_limit) {
        this.sessions_limit = sessions_limit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("APIStatus [used_time=");
        builder.append(used_time);
        builder.append(", total_available_time=");
        builder.append(total_available_time);
        builder.append(", running_sessions=");
        builder.append(running_sessions);
        builder.append(", sessions_limit=");
        builder.append(sessions_limit);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

}
