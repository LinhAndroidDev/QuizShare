package com.example.appthitracnghiem.domain.usecase;

import com.example.appthitracnghiem.domain.repository.AuthRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086B\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/example/appthitracnghiem/domain/usecase/LoginUseCase;", "", "authRepository", "Lcom/example/appthitracnghiem/domain/repository/AuthRepository;", "(Lcom/example/appthitracnghiem/domain/repository/AuthRepository;)V", "invoke", "Lcom/example/appthitracnghiem/core/ResultState;", "Lcom/example/appthitracnghiem/domain/model/AuthSession;", "loginId", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_developmentDebug"})
public final class LoginUseCase {
    private final com.example.appthitracnghiem.domain.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject
    public LoginUseCase(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull
    java.lang.String loginId, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.domain.model.AuthSession>> continuation) {
        return null;
    }
}