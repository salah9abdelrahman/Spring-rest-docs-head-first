package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath("/foo")
        body([[
                      'name': 'salah'
              ]])
    }
    response {
        status(OK())
        body([[
                      'name'   : 'a',
                      'surname': 'bla'
              ]])
    }
}

