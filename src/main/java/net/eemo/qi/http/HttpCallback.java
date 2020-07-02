package net.eemo.qi.http;

/**
 * When http request get result,whether if success or failure
 * , {@link HttpTool} will be invoke {@code handle(Object result)}
 *
 * @author liyu0
 */
public interface HttpCallback {

    Object handle(Object result);

}
