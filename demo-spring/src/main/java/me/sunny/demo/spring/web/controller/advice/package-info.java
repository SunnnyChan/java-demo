/**
 * @ControllerAdvice
 * @RestControllerAdvice
 *
 * Pitfall:
 * 1. @ControllerAdvice 和 @RestControllerAdvice 处理异常时，
 *    指定处理的异常如果存在集成关系，会导致无法走具体异常处理的逻辑（会走处理父类异常的逻辑）。
 */
package me.sunny.demo.spring.web.controller.advice;