package co.krypt.kryptonite.pgp.publickey;

import co.krypt.kryptonite.pgp.PGPException;

/**
 * Created by Kevin King on 6/12/17.
 * Copyright 2016. KryptCo, Inc.
 */

public class UnsupportedPublicKeyVersionException extends PGPException {
    UnsupportedPublicKeyVersionException(String message) {
        super(message);
    }
    UnsupportedPublicKeyVersionException() {
        super();
    }
}
