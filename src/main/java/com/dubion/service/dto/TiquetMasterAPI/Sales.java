
package com.dubion.service.dto.TiquetMasterAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sales {

    @SerializedName("public")
    @Expose
    private Public _public;

    public Public getPublic() {
        return _public;
    }

    public void setPublic(Public _public) {
        this._public = _public;
    }

}
