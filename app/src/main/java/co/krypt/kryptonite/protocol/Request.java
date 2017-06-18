package co.krypt.kryptonite.protocol;

import com.amazonaws.util.Base32;
import com.github.zafarkhaja.semver.Version;
import com.google.gson.annotations.SerializedName;

import co.krypt.kryptonite.crypto.SHA256;
import co.krypt.kryptonite.exception.CryptoException;
import co.krypt.kryptonite.pairing.Pairing;

/**
 * Created by Kevin King on 12/3/16.
 * Copyright 2016. KryptCo, Inc.
 */

public class Request {
    @SerializedName("request_id")
    @JSON.JsonRequired
    public String requestID;

    public String requestIDCacheKey(Pairing pairing) throws CryptoException {
        return Base32.encodeAsString(SHA256.digest((pairing.getUUIDString().toLowerCase() + requestID).getBytes())).toLowerCase().replace("=", "-");
    }

    @SerializedName("v")
    @JSON.JsonRequired
    public String version;

    @SerializedName("unix_seconds")
    @JSON.JsonRequired
    public Long unixSeconds;

    @SerializedName("me_request")
    public MeRequest meRequest;

    @SerializedName("sign_request")
    public SignRequest signRequest;

    @SerializedName("git_sign_request")
    public GitSignRequest gitSignRequest;

    @SerializedName("unpair_request")
    public UnpairRequest unpairRequest;

    @SerializedName("a")
    public Boolean sendACK;

    public Version semVer() {
        try {
            return Version.valueOf(version);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return Version.valueOf("0.0.0");
    }

}
